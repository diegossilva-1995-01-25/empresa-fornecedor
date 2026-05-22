import { CommonModule } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-fornecedor',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './fornecedor.html',
  styleUrl: './fornecedor.scss',
})
export class Fornecedor {

  private router = inject(Router);
  private http = inject(HttpClient);

  public errorMessage: string | null = null;

  public form = new FormGroup({
      cpfCnpj: new FormControl(),
      nome: new FormControl(),
      email: new FormControl(),
      cep: new FormControl(),
      rg: new FormControl(),
      nasc: new FormControl(),
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
      cpfCnpj: this.form.value.cpfCnpj,
      nome: this.form.value.nome,
      email: this.form.value.email,
      cep: this.form.value.cep,
      rg: this.form.value.rg,
      nasc: this.form.value.nasc,
      usuario: {
        cpf: localStorage.getItem('cpf')
      }
    };

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('authToken')
    });

    const url = 'http://192.168.18.3:8080/empresa-fornecedor/fornecedor/cadastrar';

    this.http.post(url, payload, { headers }).subscribe({
      next: (res) => {
        console.log('Fornecedor added with success', res);
        // navigate to app login screen
        this.redirectHome();
      },
      error: (err) => {
        console.error('Saving error', err);
        // Friendly message for UI
        this.errorMessage = err?.error?.message || 'Saving failed. Check for any invalid field.';
      },
    });

  }

}
