import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/services/cart.service';
import { CartItem } from 'src/app/services/common/cart-item';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.scss']
})
export class OrderDetailsComponent implements OnInit {
  cartItems: CartItem[] = []
  totalprice: number = 0;
  totalquantity: number = 0;
  taxes:number=0;
  Discount:number=0;
  grandTotal!: number;
  address1!: any;
  address2!: any;
  City!: any;
  State!: any;
  Zip!: any;
  Payment!: any;
  username!: string;
  constructor(private cartservice:CartService ,public loginService:LoginService) { }

  ngOnInit(): void {
    this.getUserAddress()
    this.cartDetails()
    this.priceCalculation()
   
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

  priceCalculation(){
    this.cartservice.priceCalculation()
    this.taxes=this.cartservice.taxes;
    this.Discount=this.cartservice.Discount
    this.grandTotal=this.cartservice.grandTotal
  }

  getUserAddress(){
   this.address1=(localStorage.getItem('address1'))
    this.address2=(localStorage.getItem('address2'))
    this.State=(localStorage.getItem('State'))
    this.City=(localStorage.getItem('City'))
    this.Zip=(localStorage.getItem('Zip'))
    this.Payment=(localStorage.getItem('Payment'))

  
}
clearCart()
{

  this.cartservice.emptyCart();
  localStorage.clear;
}
}