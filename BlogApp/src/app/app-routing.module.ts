import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddBlogComponent } from './add-blog/add-blog.component';
import { CommentsComponent } from './comments/comments.component';
import { EditBlogComponent } from './edit-blog/edit-blog.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { MyBlogsComponent } from './my-blogs/my-blogs.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { ChangesCheckGuard } from './services/changes-check.guard';
import { LoginCheckGuard } from './services/login-check.guard';
import { LogoutCheckGuard } from './services/logout-check.guard';
import { SignUpComponent } from './sign-up/sign-up.component';

const routes: Routes = [
  {
    path:"",
    component:LoginComponent,
    canActivate:[LogoutCheckGuard]
  },{
    path:"signUp",
    component:SignUpComponent,
    canActivate:[LogoutCheckGuard]
    },{
    path:"home",
    component:HomeComponent
  },{
    path:"myBlogs",
    component:MyBlogsComponent,
    canActivate:[LoginCheckGuard]
  },{
    path:"addBlogs",
    component:AddBlogComponent,
    canActivate:[LoginCheckGuard]
  },{
    path:"comments",
    component:CommentsComponent,
    canActivate:[LoginCheckGuard],
    canDeactivate:[ChangesCheckGuard]
  },
  {
    path:"editBlog",
    component:EditBlogComponent,
    canActivate:[LoginCheckGuard]
  },{
    path:"**",
    component:PageNotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
