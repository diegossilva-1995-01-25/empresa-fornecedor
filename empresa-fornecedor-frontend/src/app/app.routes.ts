import { Routes } from '@angular/router';
import { Login } from './public/login/login';
import { Registrar } from './public/registrar/registrar';
import { Menu } from './private/menu/menu';
import { Empresa } from './private/empresa/empresa';
import { ListaEmpresas } from './private/lista-empresas/lista-empresas';
import { Fornecedor } from './private/fornecedor/fornecedor';
import { ListaFornecedores } from './private/lista-fornecedores/lista-fornecedores';
import { EmpresaFornecedor } from './private/empresa-fornecedor/empresa-fornecedor';
import { Home } from './private/home/home';

export const routes: Routes = [
    { path: '', redirectTo: 'login', pathMatch: 'full' },

    // Public pages
    { path: 'login', component: Login },
    { path: 'registrar', component: Registrar },

    // Private area (menu is the layout; children are the private pages)
    {
        path: 'private',
        component: Menu,
        //canActivate: [AuthGuard], // optional: only if you have an AuthGuard
        children: [
            { path: '', redirectTo: 'home', pathMatch: 'full' },
            { path: 'empresa', component: Empresa },
            { path: 'lista-empresas', component: ListaEmpresas },
            { path: 'fornecedor', component: Fornecedor },
            { path: 'lista-fornecedores', component: ListaFornecedores },
            { path: 'empresa-fornecedor', component: EmpresaFornecedor },
            { path: 'home', component: Home },
        ]
    },

    // Wildcard
    { path: '**', redirectTo: 'login' }
];