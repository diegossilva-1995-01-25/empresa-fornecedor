import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaFornecedores } from './lista-fornecedores';

describe('ListaFornecedores', () => {
  let component: ListaFornecedores;
  let fixture: ComponentFixture<ListaFornecedores>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListaFornecedores],
    }).compileComponents();

    fixture = TestBed.createComponent(ListaFornecedores);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
