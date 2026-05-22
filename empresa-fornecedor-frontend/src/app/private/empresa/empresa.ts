import { CommonModule } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-empresa',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './empresa.html',
  styleUrl: './empresa.scss',
})
export class Empresa {

  private router = inject(Router);
  private http = inject(HttpClient);

  public errorMessage: string | null = null;

  public form = new FormGroup({
      cnpj: new FormControl(),
      nomeFantasia: new FormControl(),
      cep: new FormControl(),
  });

  redirectHome(): void {
    this.router.navigate(['/private/home']);
  }

  onSubmit(): void {

    this.errorMessage = null;
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const payload = {
      cnpj: this.form.value.cnpj,
      nomeFantasia: this.form.value.nomeFantasia,
      cep: this.form.value.cep,
      usuario: {
        cpf: localStorage.getItem('cpf')
      }
    };

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('authToken')
    });

    const url = 'http://192.168.18.3:8080/empresa-fornecedor/empresa/cadastrar';

    this.http.post(url, payload, { headers }).subscribe({
      next: (res) => {
        console.log('Empresa added with success', res);
        // navigate to app login screen
        this.redirectHome();
      },
      error: (err) => {
        console.error('Saving error', err);
        // Friendly message for UI
        this.errorMessage = err?.error?.message || 'Saving failed. Check CNPJ, nome fantasia and CEP.';
      },
    });

  }

}
