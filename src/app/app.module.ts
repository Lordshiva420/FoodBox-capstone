import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MdbCollapseModule } from 'mdb-angular-ui-kit/collapse';
import { SignupComponent } from './components/user/signup/signup.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from  '@angular/common/http';
import { LoginComponent } from './components/user/login/login.component';
import { authInterceptorProvider } from './services/Authentication/AuthInterceptor';
import { UserDashboardComponent } from './pages/user-dashboard/user-dashboard.component';

import { SearchComponent } from './components/search/search.component';
import { CartStatusComponent } from './components/cart-status/cart-status.component';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { SidemenuComponent } from './components/sidemenu/sidemenu.component';
import { CartDetailsComponent } from './components/cart-details/cart-details.component';
import { CheckoutComponent } from './pages/checkout/checkout.component';
import { CURDproductComponent } from './pages/curdproduct/curdproduct.component';
import { OrderDetailsComponent } from './pages/order-details/order-details.component';


@NgModule({
  declarations: [
    AppComponent,
    SignupComponent,
    NavbarComponent,
    LoginComponent,
    UserDashboardComponent,
    SearchComponent,
    CartStatusComponent,
    ProductDetailsComponent,
    SidemenuComponent,
    CartDetailsComponent,
    CheckoutComponent,
    CURDproductComponent,
    OrderDetailsComponent,
 
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MdbCollapseModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [authInterceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
