import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { EmpresaService } from '../service/empresa.services';
import { FornecedorService } from '../service/fornecedor.services';

@Component({
  selector: 'app-home',
  imports: [],
  templateUrl: './home.html',
  styleUrl: './home.scss',
})
export class Home {

  private http = inject(HttpClient);
  private router = inject(Router);

  fornecedores: any;
  empresas: any;

  constructor(private empresaService: EmpresaService, private fornecedorService: FornecedorService) {
    
    this.empresaService.recuperarEmpresas().subscribe((data: any) => {
      this.empresas = data;
    });
    
    this.fornecedorService.recuperarFornecedores().subscribe((data: any) => {
      this.fornecedores = data;
    });
  }

  ngOnInit() {

    setTimeout(() => {
      console.log(this.empresas);
      console.log(this.fornecedores);
    }, 100);

  }



}
