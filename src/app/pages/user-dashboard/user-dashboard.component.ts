import { Component,OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { CartService } from 'src/app/services/cart.service';
import { CartItem } from 'src/app/services/common/cart-item';
import { Product } from 'src/app/services/common/product';
import { CurdService } from 'src/app/services/curd.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.scss']
})
export class UserDashboardComponent implements OnInit {

  Productlist: Product[] = [];
  currentCategoryId: number = 0;
  searchMode: boolean = false;
  isEnable!: boolean;


  constructor(private productService: ProductService, private activtedRoute: ActivatedRoute, private cartService: CartService, private crudservice: CurdService) { }


  ngOnInit(): void {

    this.activtedRoute.paramMap.subscribe(() =>

      this.handleMethods()
    )

  }

  handleMethods() {

    this.searchMode = this.activtedRoute.snapshot.paramMap.has("keyword");

    if (this.searchMode) {
      // handle search method
      this.handleSearchProduct();
    } else {
      // handle getproducts method
      this.handleGetProducts();
    }
  }


  // get products to display both All and category wise
  handleGetProducts() {

    const hasCategoryId: boolean = this.activtedRoute.snapshot.paramMap.has('id');

    if (hasCategoryId) {
      this.currentCategoryId = Number(this.activtedRoute.snapshot.paramMap.get('id'));
    } else {

      this.currentCategoryId = 0;
    }

    if (this.currentCategoryId == 0) {
      // get all the products
      this.productService.getAllProducts().subscribe({

        next: (data: any) => this.Productlist = data

      })
     
    } else {
      //get the products related to that category
      this.productService.getProductsBycat(this.currentCategoryId).subscribe(
        (data: any) => {
          console.log(data.products)
          this.Productlist = data.products
        })
    }

  }



  //search products
  handleSearchProduct() {
    const keyword: string = this.activtedRoute.snapshot.paramMap.get('keyword') as string;
    this.productService.searchProducts(keyword).subscribe(

      (data: any) => this.Productlist = data);

  }


  // *****************************//

  // addto cart
  addTocart(product: Product) {
    console.log(product.unitPrice, product.quantity)
    const cartitem = new CartItem(product);
    this.cartService.addToCart(cartitem)
  }

}
