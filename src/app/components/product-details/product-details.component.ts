import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserDashboardComponent } from 'src/app/pages/user-dashboard/user-dashboard.component';
import { CartService } from 'src/app/services/cart.service';
import { CartItem } from 'src/app/services/common/cart-item';
import { Product } from 'src/app/services/common/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.scss']
})
export class ProductDetailsComponent implements OnInit {

  product: Product = new Product;

  constructor(private activatedRoute: ActivatedRoute, private productService: ProductService,private cartservice:CartService) { }

  ngOnInit(): void {
    this.getProductDetails();
  }


  getProductDetails() {
    const id: number = Number(this.activatedRoute.snapshot.paramMap.get("id"))
    console.log(id)
    this.productService.getProductdetail(id).subscribe(

      (data: any) => {
        console.log(data)
        this.product = data;
      }
    )

  }


  addtocart(product:Product){

    const cartitem=new CartItem(product);
    this.cartservice.addToCart(cartitem);

  }
  
}
