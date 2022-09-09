import { Product } from "./product";

export class CartItem {

    pId!: number;
    productName!: string;
    productDescription!: string;
    imageUrl!: string;
    unitPrice!: number;
    quantity!: number;
    active!: boolean;

    constructor(Product: Product ){

       this.pId=Product.pId;
       this.productName=Product.productName;
       this.productDescription=Product.productDescription;
       this.quantity=1;
       this.unitPrice=Product.unitPrice;
       this.imageUrl=Product.imageUrl;
       this.active=Product.active;


    }
}
