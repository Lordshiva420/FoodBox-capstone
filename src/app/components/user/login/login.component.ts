import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { NavbarComponent } from '../../navbar/navbar.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  error:boolean=false;
  loginData = {
    userName: "",
    password: ""
  }

  constructor(private loginService: LoginService, private router: Router) { }

  ngOnInit(): void {
  }


  loginSubmit() {
    console.log(this.loginData);
    if (this.loginData.userName != "" && this.loginData.password != "") {
      this.loginService.generateToken(this.loginData).subscribe({

        next:(data:any)=>{
          //token generated
          console.log("token generation success"+data.token);

         //login
         this.loginService.login(data.token);
         //getcurrentUser
         this.loginService.getCurrentUser().subscribe({
          next:(user:any)=>{
            console.log(user);
            this.loginService.setUser(user);

            //redirect the user according to role..
           
          if(this.loginService.IsUserloggedIn()){
              this.loginService.adminDisplay()
              this.router.navigate(["userDashboard"]);

          }
              

          

        
          }
         })
      
      
      
      
      
      },
        error:(e)=>{console.log(e)
          this.error=true;}

      })

    }
  }
}
function elseif(arg0: boolean) {
  throw new Error('Function not implemented.');
}

