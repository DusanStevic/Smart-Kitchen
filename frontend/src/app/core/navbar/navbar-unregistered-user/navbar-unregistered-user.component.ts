import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-navbar-unregistered-user',
  templateUrl: './navbar-unregistered-user.component.html',
  styleUrls: ['./navbar-unregistered-user.component.scss']
})
export class NavbarUnregisteredUserComponent implements OnInit {

  constructor(private router: Router, private toastr: ToastrService) {
  }

  ngOnInit() {
  }

}
