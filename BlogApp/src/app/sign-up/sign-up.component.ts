import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent {
  hide = true;

  constructor(private us:UserService, private fb:FormBuilder, private r:Router ){}

  phonePattern = '^[7-9][0-9]{9}$';
  emailpattern='^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$'

  signUpForm = this.fb.group({
    user_name: ['',Validators.required],
    user_email: ['',[Validators.required,Validators.email]],
    user_password: ['',[Validators.required,Validators.pattern("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,}$")]],
    user_contact: ['',[Validators.required,Validators.pattern(this.phonePattern)]],
    })

    get name(){
      return this.signUpForm.get('user_name');
    }
    get email(){
      return this.signUpForm.get('user_email');
    }
    get contact(){
      return this.signUpForm.get('user_contact');
    }
    get password(){
      return this.signUpForm.get('user_password');
    }
    get signUpFormControl() {
      return this.signUpForm.controls;
    }


  responseData:any;
  signUp() { 
    console.log(this.signUpForm.value);
    this.us.registerUser(this.signUpForm.value).subscribe({
      next:data=>Swal.fire(
          'Singed Up successfully',
          '',
          'success'
        ).then(()=>this.r.navigate(['/home'])),
      error:e=>Swal.fire(
        'User Already Exists',
        'Try Again',
        'error'
      )
    });
  }


}
