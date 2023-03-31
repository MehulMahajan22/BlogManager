import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  
  
  ngOnInit(): void {  } 

  hide=true;

  constructor(private us:UserService, private r: Router){}

  loginForm = new FormGroup({
    'user_email':new FormControl(),
    'user_password':new FormControl()
  })
  responseData:any;

  checkLogin(){
    console.log(this.loginForm.value);
      this.us.login(this.loginForm.value).subscribe({
        next:data=>{this.responseData=data
        localStorage.setItem("Token",this.responseData.Token);
        localStorage.setItem("Email",this.responseData.Email);
        this.r.navigate(['/myBlogs']);},
        error:error=>{
          Swal.fire(
            'Invalid Credentials',
            'Try Again',
            'error'
          )
        }
      });
  }


}
