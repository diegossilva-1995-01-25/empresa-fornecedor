import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registrar',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './registrar.html',
  styleUrl: './registrar.scss',
})
export class Registrar {

  private router = inject(Router);
  private http = inject(HttpClient);

  public errorMessage: string | null = null;

  public form = new FormGroup({
      nome: new FormControl(),
      cpf: new FormControl(),
      senha: new FormControl(),
      confSenha: new FormControl(),
  });

  redirectLogin(): void {
    this.router.navigate(['/login']);
  }

  onSubmit(): void {

    this.errorMessage = null;
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    if (this.form.value.senha != this.form.value.confSenha) {
      this.errorMessage = "Senhas não coincidem";
      return;
    }

    const payload = {
      cpf: this.form.value.cpf,
      senha: this.form.value.senha,
      nome: this.form.value.nome,
    };

    const url = 'http://192.168.18.3:8080/empresa-fornecedor/api/auth/registrar';

    this.http.post(url, payload).subscribe({
      next: (res) => {
        console.log('Signin success', res);
        // navigate to app login screen
        this.redirectLogin();
      },
      error: (err) => {
        console.error('Signin error', err);
        // Friendly message for UI
        this.errorMessage = err?.error?.message || 'Signin failed. Check CPF, nome and senha.';
      },
    });

  }

}
