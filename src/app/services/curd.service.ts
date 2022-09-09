import { HttpClient } from '@angular/common/http';
import { EventEmitter, Injectable } from '@angular/core';
import { cate } from './common/addproduct';
import { Product } from './common/product';

@Injectable({
  providedIn: 'root'
})
export class CurdService {

  constructor(private http: HttpClient) { }

  private baseUrl = "http://localhost:8080";

  // add the product to cart
  addProduct(formData: any) {

    return this.http.post(`${this.baseUrl}/product/upload`, formData)

  }

  // add category to cart
  addCategory(category: cate) {

    return this.http.post(`${this.baseUrl}/category/addcategory`, category)
  }

  //update the product details
  updateProduct(pro: Product) {

    return this.http.put(`${this.baseUrl}/product/updateProduct`, pro)
  }

  //update category details
  updateCategory(category: cate) {

    return this.http.put(`${this.baseUrl}/category/updatecategory`, category)
  }

  // delete the product
  deleteProduct(pId: number) {

    return this.http.delete(`${this.baseUrl}/product/deleteproduct/${pId}`)
  }

  //delete the category and its products
  deleteCategory(cId: number) {

    return this.http.delete(`${this.baseUrl}/category/deleteCategory/${cId}`)
  }



}
