import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';




@Injectable({
  providedIn: 'root'
})

export class ProductService {

  private baseUrl = "http://localhost:8080";

  constructor(private http: HttpClient) { }

  // get all products
  getAllProducts() {

    return this.http.get(`${this.baseUrl}/product/getallproducts`);

  }
  // get product by id

  getProductdetail(id: number) {

    return this.http.get(`${this.baseUrl}/product/productId/${id}`)
  }
  // search products
  searchProducts(keyword: string) {

    return this.http.get(`${this.baseUrl}/product/searchproduct/${keyword}`);

  }
  //get products by category
  getProductsBycat(catId: any) {

    return this.http.get(`${this.baseUrl}/category/getCategoryByid/${catId}`)

  }

  //get all categories
  getAllcategories() {

    return this.http.get(`${this.baseUrl}/category/getallcategories`)
  }

}
