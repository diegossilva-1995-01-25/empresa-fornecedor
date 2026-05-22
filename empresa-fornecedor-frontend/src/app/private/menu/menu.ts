import { Component, inject } from '@angular/core';
import { Router, RouterOutlet } from "@angular/router";

@Component({
  selector: 'app-menu',
  imports: [RouterOutlet],
  templateUrl: './menu.html',
  styleUrl: './menu.scss',
})
export class Menu {

  private router = inject(Router);

  redirectHome(): void {
    this.router.navigate(['/private/home']);
  }

  redirectEmpresa(): void {
    this.router.navigate(['/private/empresa']);
  }

  redirectFornecedor(): void {
    this.router.navigate(['/private/fornecedor']);
  }

  redirectListaEmpresas(): void {
    this.router.navigate(['/private/lista-empresas']);
  }

  redirectListaFornecedores(): void {
    this.router.navigate(['/private/lista-fornecedores']);
  }

  redirectLogin(): void {
    this.router.navigate(['/login']);
  }

}
