import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { CartService } from 'src/app/services/cart.service';
import { CartItem } from 'src/app/services/common/cart-item';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss']
})
export class CheckoutComponent implements OnInit {
  cartItems: CartItem[] = []
  myAddressForm!: FormGroup;
  totalprice: number = 0;
  totalquantity: number = 0;
  taxes:number=0;
  Discount:number=0;
  grandTotal!: number;
 
  constructor(private formbuilder: FormBuilder,private cartservice:CartService,private route:Router) { }

  ngOnInit(): void {

    this.myAddressForm = this.formbuilder.group({
      address1: [''],
      address2: [''],
      City: [''],
      State: [''],
      Zip: [''],
      Payment: ['']
    })
    this.cartItems = this.cartservice.cartItems;
    this.cartDetails()
    this.priceCalculation()
    
  }


  formSubmit(){
    console.log('form submitted')
    localStorage.setItem("address1",this.myAddressForm.get('address1')?.value)
    localStorage.setItem("address2",this.myAddressForm.get('address2')?.value)
    localStorage.setItem("City",this.myAddressForm.get('City')?.value)
    localStorage.setItem("State",this.myAddressForm.get('State')?.value)
    localStorage.setItem("Zip",this.myAddressForm.get('Zip')?.value)
    localStorage.setItem("Payment",this.myAddressForm.get('Payment')?.value)
    this.route.navigate(['OrderDetails'])

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

 
}
