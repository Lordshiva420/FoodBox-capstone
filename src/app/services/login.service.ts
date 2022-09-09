import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})

export class LoginService {
  isAdminLoggedIn:boolean=false;

  private baseUrl = "http://localhost:8080";
  isSetUser:boolean=false;

  constructor(private http:HttpClient) { }

//generate token

  public generateToken(logindata:any){

  return this.http.post(`${this.baseUrl}/generate-token`, logindata);

  }


  //get current user from server
  public getCurrentUser(){
    return this.http.get(`${this.baseUrl}/currentUser`);
  }


  //userlogin ,set the token in localstorage

  public login(token:any){
    localStorage.setItem("token",token);
    
    return true;
  }

  //userlogout, remove the token from localstorage

  public logout(){
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    return true;
  }

  //checking if user loggedin

  public IsUserloggedIn(){

    let tokenstr=localStorage.getItem("token");
    if(tokenstr!=""&&tokenstr!=undefined&& tokenstr!=null&& this.isSetUser||this.isUserPresent()){
      return true;

    }else{
      return false;
    }
  }

  //get token
  public getToken(){
    return localStorage.getItem("token");
  }
  
  //set user to localstorage
  public setUser(user:any){

    localStorage.setItem("user", JSON.stringify(user));
    this.isSetUser=true;
    return true;
  }
  //is user still present(i.e userdetails still stord in local storage)
  public isUserPresent(){
    let UserInLocal=localStorage.getItem('user');
    if(UserInLocal!=null&&UserInLocal!=undefined&&UserInLocal!=""){
      return true;
    }else{
      return false;
    }
  }

  //get user from localstorage
  public getUser(){
    let userStr= localStorage.getItem("user");
    if(userStr!=null){
      return JSON.parse(userStr);
    }else{
      this.logout();
      return null;
    }
  }


  //get user role
  public getUserRole(){

    let user=this.getUser();

    return user.authorities[0].authority;
  }

  // diplay manage products for admin
  public adminDisplay(){
    if(this.IsUserloggedIn()&& this.getUserRole()=='admin'){
      this.isAdminLoggedIn=true;
    }else{
      this.isAdminLoggedIn=false;
    }
  }
}
