import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient:HttpClient) { }

  baseUrl='http://localhost:9999/';

  login(loginData:any){
    return this.httpClient.post(this.baseUrl+"user/login",loginData);
  }

  registerUser(signUpData:any){
    return this.httpClient.post(this.baseUrl+"user/registerUser",signUpData);
  }

  getAllBlogs(){
    let httpHeaders = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('Token')
    });
    let requestOptions = {headers : httpHeaders}
    return this.httpClient.get(this.baseUrl+"blog/getAllBlogs",requestOptions)
  }

  getMyBlogs(){
    let httpHeaders = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('Token')
    });
    let requestOptions = {headers : httpHeaders}
    return this.httpClient.get(this.baseUrl+"blog/myBlogs",requestOptions)
  }

  addBlog(blog:any){
    let httpHeaders = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('Token')
    });
    let requestOptions1 = {headers : httpHeaders}
    console.log(localStorage.getItem('Token'))
    return this.httpClient.post(this.baseUrl+"blog/addBlog",blog,requestOptions1);
  }

  deleteBlog(blog:any){
    let httpHeaders = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('Token')
    });
    let requestOptions1 = {headers : httpHeaders,
    body: blog}
    return this.httpClient.delete(this.baseUrl+"blog/deleteBlog",requestOptions1)
  }

  getBlog(title:any){
    let httpHeaders = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('Token')
    });
    let requestOptions1 = {headers : httpHeaders}
    return this.httpClient.get(this.baseUrl+"blog/getBlog/"+title,requestOptions1)
  }

  updateBlog(blog:any){
    let httpHeaders = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('Token')
    });
    let requestOptions1 = {headers : httpHeaders}
    return this.httpClient.put(this.baseUrl+"blog/updateBlog",blog,requestOptions1)
  }

  updateComments(requestObject:any){
    let httpHeaders = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('Token')
    });
    let requestOptions1 = {headers : httpHeaders}
    return this.httpClient.put(this.baseUrl+"blog/comments",requestObject,requestOptions1)
  }

  getBlogForComment(title:any, email:any){
    let httpHeaders = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('Token')
    });
    let requestOptions1 = {headers : httpHeaders }
    return this.httpClient.post(this.baseUrl+"blog/getBlogComment/"+title,email,requestOptions1)
  }

  saveComment(comment:any,blog:any){
    let httpHeaders = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem('Token')
    });
    let requestOptions1 = {headers : httpHeaders}
    return this.httpClient.post(this.baseUrl+"blog/comments/"+comment,blog,{responseType:'text'})
  }

}
