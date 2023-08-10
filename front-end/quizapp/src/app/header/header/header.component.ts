import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginFormComponent } from 'src/app/pages/home/components/login-form/login-form.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

  constructor(private matDiaglog: MatDialog) {}

  openDialog() {
    this.matDiaglog.open(LoginFormComponent, {width: '550px'})
    }
  }

