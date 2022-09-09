import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { CartItem } from './common/cart-item';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  cartItems: CartItem[] = [];
  totalPrice: Subject<number> = new Subject<number>;
  totalQuantity: Subject<number> = new Subject<number>;
  taxes!: number;
  Discount!: number;
  TotalPrice!: number;
  grandTotal!: any;

  constructor() { }

//add cartitem to cartlist depending on whether it already exists in cart or not
  addToCart(cartItem: CartItem) {
    let alreadyExistsInCart: boolean = false;
    let existingCartItem: any = undefined;

    if (this.cartItems.length > 0) {

      existingCartItem = this.cartItems.find(tempcartItem => tempcartItem.pId === cartItem.pId)

      alreadyExistsInCart = (existingCartItem != undefined);
    }

    if (alreadyExistsInCart) {

      existingCartItem.quantity++;
    } else {
      this.cartItems.push(cartItem);
    }

    this.calculateTotalPrice();

  }


  //decrement quantity of cartitem
  decrementQuantity(cartitem: CartItem) {
    cartitem.quantity--;
    if (cartitem.quantity === 0) {
      this.removeCartItem(cartitem)
    } else {
      this.calculateTotalPrice();
    }


  }

//remove the cartitem from listofcartitems
  removeCartItem(cartitem: CartItem) {

    const itemIndex = this.cartItems.findIndex(temcartItem => temcartItem.pId == cartitem.pId)
     if (itemIndex > -1) {
      this.cartItems.splice(itemIndex, 1);
      this.calculateTotalPrice();
    }
  }

  // empty cart
  emptyCart(){
    this.cartItems=[];
    this.calculateTotalPrice();
  }

  // after all opertions we will excute this method to update quantity and price of cartitems
  
  calculateTotalPrice() {

    let totalpricevalue: number = 0;
    let totalquantityvalue: number = 0;

    for (let currentCartItem of this.cartItems) {

      totalquantityvalue += currentCartItem.quantity;
      totalpricevalue += currentCartItem.quantity * currentCartItem.unitPrice;
    }
    this.TotalPrice=totalpricevalue;
    this.totalPrice.next(totalpricevalue)
    this.totalQuantity.next(totalquantityvalue)
  }

  priceCalculation(){
    this.taxes=20;
    this.Discount=5;
    this.grandTotal=(this.TotalPrice*(1+this.taxes/100)*(1-this.Discount/100))
  }
}
