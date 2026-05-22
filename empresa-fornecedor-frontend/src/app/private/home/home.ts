import { Component, OnInit, ChangeDetectorRef, inject } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { EmpresaService } from '../service/empresa.services';
import { FornecedorService } from '../service/fornecedor.services';
import { Router } from '@angular/router';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-home',
  imports: [],
  templateUrl: './home.html',
  styleUrls: ['./home.scss'],
})
export class Home implements OnInit {

  private router = inject(Router);
  private http = inject(HttpClient);

  fornecedores: any[] = [];
  empresas: any[] = [];

  constructor(
    private empresaService: EmpresaService,
    private fornecedorService: FornecedorService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {

    const cpf = localStorage.getItem('cpf');

    if (!cpf) return;

    this.empresaService.recuperarEmpresas(cpf)
      .subscribe({
        next: (data: any) => {

          this.empresas = data ?? [];

          this.cdr.detectChanges();
        },
        error: (err: any) => console.error(err)
      });

    this.fornecedorService.recuperarFornecedores(cpf)
      .subscribe({
        next: (data: any) => {

          this.fornecedores = data ?? [];

          this.cdr.detectChanges();
        },
        error: (err: any) => console.error(err)
      });

  }

  deletarFornecedor(fornecedor: any) {

    const payload = {
        cpfCnpj: fornecedor.cpfCnpj,
    }

    const headers = new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('authToken')
    });

    const url = `${environment.apiUrl}/fornecedor/deletar`;

    this.http.post(url, payload, { headers }).subscribe({
      complete: () => {
        location.reload();
      }
    });

  }

  redirectEditarFornecedor(fornecedor: any) {

    localStorage.setItem('fornecedor', JSON.stringify(fornecedor));

    setTimeout(() => {
      this.router.navigate(['/private/fornecedor']);
    }, 100);

  }

  deletarEmpresa(empresa: any) {

    const payload = {
        cnpj: empresa.cnpj,
    }

    const headers = new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('authToken')
    });

    const url = `${environment.apiUrl}/empresa/deletar`;

    this.http.post(url, payload, { headers }).subscribe({
      complete: () => {
        location.reload();
      }
    });

  }

  redirectEditarEmpresa(empresa: any) {

    localStorage.setItem('empresa', JSON.stringify(empresa));

    setTimeout(() => {
      this.router.navigate(['/private/empresa']);
    }, 100);

  }

}