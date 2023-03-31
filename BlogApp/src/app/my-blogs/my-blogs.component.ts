import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-my-blogs',
  templateUrl: './my-blogs.component.html',
  styleUrls: ['./my-blogs.component.css']
})
export class MyBlogsComponent {

  blogs:any;

  changesMade=0;

  constructor(private us:UserService, private r:Router){
    this.us.getMyBlogs().subscribe(data=>this.blogs=data);
    this.changesMade=0;
  }

  editBlog(blog:any){
    localStorage.setItem('Title',blog.blog_title);
    this.r.navigate(['/editBlog'])
  }

  deleteBlog(blog:any){
    this.us.deleteBlog(blog).subscribe({
      next:data=>Swal.fire(
        'Deleted successfully',
        '',
        'success'
      ).then(()=>this.us.getMyBlogs().subscribe(data=>this.blogs=data)),
      error:e=>Swal.fire(
        e.error.message,
        '',
        'error'
      )
    })
  }


}
