import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar-registered-user',
  templateUrl: './navbar-registered-user.component.html',
  styleUrls: ['./navbar-registered-user.component.scss']
})
export class NavbarRegisteredUserComponent implements OnInit {

  constructor(private router: Router, private toastr: ToastrService) {
  }

  ngOnInit() {
  }

  logOut(): void {
    localStorage.removeItem('user');
    this.toastr.success('Succesful logout!');
    location.reload();
    this.router.navigate(['']);
  }

}
