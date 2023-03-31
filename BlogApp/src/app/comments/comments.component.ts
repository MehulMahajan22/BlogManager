import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { Blog } from '../Models/Blog';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent {

  blog:Blog={};
  originalData:Blog={};

  constructor(private us:UserService, private r: Router, private fb: FormBuilder){}
  ngOnInit(){
    const title=localStorage.getItem('Title');
    const email = localStorage.getItem('Blogger_Email')
    this.us.getBlogForComment(title,email).subscribe({
      next:data=>{this.blog=data,this.originalData=data, this.count=0},
      error:e=>Swal.fire(
        e.error.message,
        '',
        'error'
      )
    })
  }

  commentForm = this.fb.group({
    comment:['',Validators.required]
  })

  count=0;

  canDeactivate(){
    if(this.count==0)
    return Swal.fire({
      title: 'Are you sure?',
      text: "You are leaving without making any comments",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes'
    }).then((result) =>(result.isConfirmed) ? true : false);
    else{
    console.log('else');
      return true
    }
  }


  
  submitComments(){
   this.count++;
   this.us.saveComment(this.commentForm.value.comment,this.blog).subscribe({
    next:()=>Swal.fire(
      'Blog Updated successfully',
      '',
      'success'
    ).then(()=>localStorage.setItem('Title','')).then(()=>this.r.navigate(['/home'])),
    error:e=>console.log(e)
   })
  }

}
