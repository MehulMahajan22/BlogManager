import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Blog } from '../Models/Blog';
import { UserService } from '../services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-edit-blog',
  templateUrl: './edit-blog.component.html',
  styleUrls: ['./edit-blog.component.css']
})
export class EditBlogComponent {
  
  blog:Blog={};
  originalData:Blog={};

  constructor(private us:UserService, private r: Router){}
  ngOnInit(){
    const title=localStorage.getItem('Title');
    this.us.getBlog(title).subscribe({
      next:data=>{this.blog=data,this.originalData=data},
      error:e=>Swal.fire(
        e.error.message,
        '',
        'error'
      )
    })
  }
     
  submitEditForm(){
   this.us.updateBlog(this.blog).subscribe({
    next:()=>Swal.fire(
      'Blog Updated successfully',
      '',
      'success'
    ).then(()=>localStorage.setItem('Title','')).then(()=>this.r.navigate(['/myBlogs'])),
    error:e=>Swal.fire(
      e.error.message,
      '',
      'error'
    )
   })
  }
  

}


