import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { addProduct, cate } from 'src/app/services/common/addproduct';
import { Category } from 'src/app/services/common/category';
import { Product } from 'src/app/services/common/product';

import { CurdService } from 'src/app/services/curd.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-curdproduct',
  templateUrl: './curdproduct.component.html',
  styleUrls: ['./curdproduct.component.scss']
})
export class CURDproductComponent implements OnInit {
  myProductForm!: FormGroup;
  myCategoryForm!: FormGroup;
  selectedFile!: File;
  product: addProduct = new addProduct();//this product has both product and category variables
  cate: cate = new cate();//only category variables
  listCategories: Category[] = []//list of category and there respective products
  Productlist: Product[] = [];//list of products
  showAddBtn: boolean = false;
  showEditBtn: boolean = false;

  constructor(private formbuilder: FormBuilder, private curdservice: CurdService, private productservice: ProductService) { }

  ngOnInit(): void {

    this.myProductForm = this.formbuilder.group({
      ProductName: [''],
      Description: [''],
      QtyAvailable: [''],
      Unitprice: [''],
      imageUrl: [''],
      categoryId: ['']
    })

    this.myCategoryForm = this.formbuilder.group({
      CategoryName: ['']

    })
    this.getCategories()

  }

  //to make product active and inactive on user page
  onToggle(e: any, pro: Product, cId: number) {
    this.product.pId = pro.pId;
    this.product.productName = pro.productName;
    this.product.productDescription = pro.productDescription;
    this.product.quantity = pro.quantity;
    this.product.unitPrice = pro.unitPrice;
    this.product.imageUrl = pro.imageUrl;
    this.product.category.cId = cId;
    if (e.target.checked) {
      this.product.active = true;
      this.updateProduct(this.product)
    } else {

      this.product.active = false;
      this.updateProduct(this.product)
    }

  }



  clickAddproduct() {
    this.myProductForm.reset();
    this.showAddBtn = true;
    this.showEditBtn = false;

  }
  clickAddCategory() {
    this.myCategoryForm.reset();
    this.showAddBtn = true;
    this.showEditBtn = false;

  }

  onFileChanged(e: any) {

    this.selectedFile = e.target.files[0];

    console.log(this.selectedFile)

  }


  // get all categories
  getCategories() {

    this.productservice.getAllcategories().subscribe(

      (data: any) => {
        console.log(data)
        this.listCategories = data;

      }
    )
  }


  // adding category
  addcategory() {

    this.cate.categoryName = this.myCategoryForm.get('CategoryName')?.value;
    console.log(this.cate);
    this.curdservice.addCategory(this.cate).subscribe(

      (data) => {
        console.log(data)
        this.getCategories()
      }
    )
    this.myCategoryForm.reset();

  }



  //on clicking add btn form gets submitted to adding product.
  onsubmit() {
    const formData = new FormData;
    console.log(JSON.stringify(this.myProductForm.value.ProductName));
    this.product.pId = 0;
    this.product.imageUrl = "";
    this.product.productName = this.myProductForm.get('ProductName')?.value;
    this.product.productDescription = this.myProductForm.get('Description')?.value;
    this.product.quantity = this.myProductForm.get('QtyAvailable')?.value;
    this.product.unitPrice = this.myProductForm.get('Unitprice')?.value;
    this.product.category.cId = this.myProductForm.get('categoryId')?.value;
    console.log(this.myProductForm.get('categoryId')?.value)
    formData.append('product', new Blob([JSON.stringify(this.product)], { type: 'application/json' }));
    formData.append('image', this.selectedFile)

    //adding product
    this.curdservice.addProduct(formData).subscribe(
      (data) => {
        console.log(data)
        this.getCategories()
      }
    );

    this.myProductForm.reset();
  }


  // onclicking edit btn of particular row of product,form will be populated 
  productEdit(row: Product, category: cate) {
    this.showAddBtn = false;
    this.showEditBtn = true;
    this.product.pId = row.pId;
    this.myProductForm.controls['ProductName'].setValue(row.productName)
    this.myProductForm.controls['Description'].setValue(row.productDescription)
    this.myProductForm.controls['QtyAvailable'].setValue(row.quantity)
    this.myProductForm.controls['Unitprice'].setValue(row.unitPrice)
    this.myProductForm.controls['categoryId'].setValue(category.cId)
    this.product.imageUrl = row.imageUrl
  }
  //on clicking edit btn of particular category
  categoryEdit(row: Category) {
    this.showAddBtn = false;
    this.showEditBtn = true;
    this.cate.cId = row.cId;
    this.myCategoryForm.controls['CategoryName'].setValue(row.categoryName)
  }



  //on making changes to form and clicking on update product
  onProductUpdate() {

    const formData = new FormData;

    this.product.productName = this.myProductForm.get('ProductName')?.value;
    this.product.productDescription = this.myProductForm.get('Description')?.value;
    this.product.quantity = this.myProductForm.get('QtyAvailable')?.value;
    this.product.unitPrice = this.myProductForm.get('Unitprice')?.value;
    this.product.category.cId = this.myProductForm.get('categoryId')?.value;
    console.log(this.myProductForm.get('categoryId')?.value)
    console.log(this.product)


    if (this.selectedFile === undefined) {
      console.log('file not uploaded')
      //update product
      this.updateProduct(this.product);

    } else {

      console.log('file  uploaded')
      formData.append('product', new Blob([JSON.stringify(this.product)], { type: 'application/json' }));
      formData.append('image', this.selectedFile)
      //adding product
      this.curdservice.addProduct(formData).subscribe(
        (data) => {
          console.log(data)
          this.getCategories()
        });
    }
  }


  //update product
  updateProduct(pro: Product) {
    this.curdservice.updateProduct(pro).subscribe(
      (data) => {
        console.log(data)
        this.getCategories()
      }
    )

  }


  //on updating the category
  onCategoryUpdate() {

    this.cate.categoryName = this.myCategoryForm.get('CategoryName')?.value;
    console.log(this.cate);
    this.curdservice.updateCategory(this.cate).subscribe(

      (data) => {
        console.log(data)
        this.getCategories()
      });
  }


  //deleting productt
  deleteProduct(pid: number) {

    this.curdservice.deleteProduct(pid).subscribe(
      (data) => {
        console.log(data)
        this.getCategories()
      });
  }
  //deleting productt
  deleteCategory(cid: number) {

    this.curdservice.deleteCategory(cid).subscribe(
      (data) => {
        console.log(data)
        this.getCategories()
      });
  }


}


