import { Component } from '@angular/core';
import { RouterOutlet } from "@angular/router";

@Component({
  selector: 'app-menu',
  imports: [RouterOutlet],
  templateUrl: './menu.html',
  styleUrl: './menu.scss',
})
export class Menu {}
