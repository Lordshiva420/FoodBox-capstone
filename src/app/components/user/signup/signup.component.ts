import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SignupService } from 'src/app/services/signup.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})

export class SignupComponent implements OnInit {
error:boolean=false;
  public user = {
    userName: "",
    password: "",
    firstName: "",
    lastName: "",
    email: "",
    phone: "",

  }
  constructor(private signupService: SignupService, private route:Router) { }

  ngOnInit(): void {
  }


  formSubmit() {

    if (this.user.userName != "" && this.user.userName != null) {
      this.signupService.addUser(this.user).subscribe({

        next: (data) => {console.log(data)
          this.error=false;
       this.route.navigate(['login'])
        },
        error: (e) => {console.error(e)
          this.error=true;
        },
        complete: () => console.info('complete')

      })

    }


  }



}
