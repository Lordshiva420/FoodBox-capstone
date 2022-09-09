import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CartDetailsComponent } from './components/cart-details/cart-details.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { LoginComponent } from './components/user/login/login.component';
import { SignupComponent } from './components/user/signup/signup.component';
import { CheckoutComponent } from './pages/checkout/checkout.component';
import { CURDproductComponent } from './pages/curdproduct/curdproduct.component';
import { OrderDetailsComponent } from './pages/order-details/order-details.component';
import { UserDashboardComponent } from './pages/user-dashboard/user-dashboard.component';
import { adminGuard } from './services/Authentication/admin.gaurd';
import { UserGuard } from './services/Authentication/user.guard';


const routes: Routes = [
  { path: 'signup', component: SignupComponent },
  { path: 'login', component:LoginComponent },
  { path: 'userDashboard', component:UserDashboardComponent},
  { path: 'cartDetails', component:CartDetailsComponent},
  { path: 'category/:id', component:UserDashboardComponent},
  { path: 'search/:keyword', component:UserDashboardComponent},
  { path: 'product/:id', component:ProductDetailsComponent},
  { path: 'checkout', component:CheckoutComponent,canActivate: [UserGuard ]},
  { path: 'manageProducts', component:CURDproductComponent,canActivate: [adminGuard]},
  { path: 'OrderDetails', component:OrderDetailsComponent,canActivate: [UserGuard ]}
  




];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
