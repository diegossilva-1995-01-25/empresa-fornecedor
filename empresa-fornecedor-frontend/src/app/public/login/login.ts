import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-login',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {

  private router = inject(Router);
  private fb = inject(FormBuilder);
  private http = inject(HttpClient);

  public errorMessage: string | null = null;

  public form = new FormGroup({
      cpf: new FormControl(),
      senha: new FormControl()
  });

  redirectLogin(): void {
    this.router.navigate(['/login']);
  }

  redirectRegistrar(): void {
    this.router.navigate(['/registrar']);
  }

  redirectMenu(): void {
    this.router.navigate(['/private/home']);
  }

  onSubmit(): void {
    this.errorMessage = null;
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const payload = {
      cpf: this.form.value.cpf,
      senha: this.form.value.senha,
    };

    const url = `${environment.apiUrl}/api/auth/login`;

    this.http.post<{ token?: string; user?: any }>(url, payload).subscribe({
      next: (res) => {
        console.log('Login success', res);
        if (res && res.token) {
          // Save token for later requests; consider using a dedicated AuthService
          localStorage.setItem('authToken', res.token);
          localStorage.setItem('cpf', payload.cpf);
        }
        // navigate to app main screen
        this.redirectMenu();
      },
      error: (err) => {
        console.error('Login error', err);
        // Friendly message for UI
        this.errorMessage = err?.error?.message || 'Login failed. Check CPF and senha.';
      },
    });
  }

}
