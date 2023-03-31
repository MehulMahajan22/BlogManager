import { Component } from '@angular/core';
import { UserService } from '../services/user.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  blogs:any;
  
  constructor(private us:UserService, private r:Router){}

  getAllProducts(){
    this.us.getAllBlogs().subscribe(data=>this.blogs=data);
  }

  ngOnInit(){ 
    this.getAllProducts();
   }

   comment(title:any,email:any){
      localStorage.setItem('Title',title)
      localStorage.setItem('Blogger_Email',email)
      console.log(email)
      this.r.navigate(['/comments']);
   }

   swalFire(){
    Swal.fire(
      'Product Added To Cart',
      '',
      'success'
    )
   }
}
