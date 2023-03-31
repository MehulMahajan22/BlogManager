import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { UserService } from '../services/user.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-blog',
  templateUrl: './add-blog.component.html',
  styleUrls: ['./add-blog.component.css']
})
export class AddBlogComponent {

  constructor(private fb: FormBuilder, private us:UserService, private r:Router){}

  addBlogForm = this.fb.group({
    blog_title: ['',Validators.required],
    blog_description: ['',Validators.required,Validators.email],
    image_url: ['',Validators.required],
    blogger_email:localStorage.getItem("Email")
    })

    addBlog(){
      this.us.addBlog(this.addBlogForm.value).subscribe({
        next:response=>Swal.fire(
          'Blog Added Successfully',
          '',
          'success'
        ).then(()=>this.r.navigate(['/myBlogs'])),
        error:e=>Swal.fire(
          e.error.message,
          '',
          'error'
        )
      })
    }

}
