import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/services/common/category';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-sidemenu',
  templateUrl: './sidemenu.component.html',
  styleUrls: ['./sidemenu.component.scss']
})
export class SidemenuComponent implements OnInit {
  categoryList: Category[] = [];
  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.getCategories();
  }

// get all categories
getCategories() {

  this.productService.getAllcategories().subscribe(

    (data: any) => {
      console.log(data)
      this.categoryList = data;

    }
  )
}
}
