import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from 'src/app/services/cart.service';
import { CartItem } from 'src/app/services/common/cart-item';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-cart-details',
  templateUrl: './cart-details.component.html',
  styleUrls: ['./cart-details.component.scss']
})
export class CartDetailsComponent implements OnInit {
  cartItems: CartItem[] = []
  totalprice: number = 0;
  totalquantity: number = 0;
 
  constructor(private cartservice: CartService,private loginservice:LoginService,private router: Router) { }

  ngOnInit(): void {
    this.cartDetails();
  }



  cartDetails() {
    this.cartItems = this.cartservice.cartItems;
    this.cartservice.totalQuantity.subscribe(
      (data) => this.totalquantity = data
    );
    this.cartservice.totalPrice.subscribe(
      (data) => this.totalprice = data
    );
    this.cartservice.calculateTotalPrice();

  }

  incrementQuantity(cartitem: CartItem){
this.cartservice.addToCart(cartitem)
  }

  decrementQuantity(cartitem: CartItem){
    this.cartservice.decrementQuantity(cartitem);
  }

  removeCartItem(cartitem: CartItem){

    this.cartservice.removeCartItem(cartitem)

  }
  checkout(){
    if(this.loginservice.IsUserloggedIn()){
      this.router.navigate(["checkout"]);
    }else{
      this.router.navigate(["login"]);
    }
  }

}
