import { Component } from '@angular/core';

@Component({
  selector: 'app-my-first-component',
  templateUrl: './my-first.component.html',
  styleUrl: './my-first.component.scss'
})
export class MyFirstComponent {
  inputValue: string = 'Hello from amigoscode';
}
