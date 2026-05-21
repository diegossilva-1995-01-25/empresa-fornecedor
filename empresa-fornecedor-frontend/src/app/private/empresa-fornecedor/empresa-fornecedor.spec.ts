import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpresaFornecedor } from './empresa-fornecedor';

describe('EmpresaFornecedor', () => {
  let component: EmpresaFornecedor;
  let fixture: ComponentFixture<EmpresaFornecedor>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmpresaFornecedor],
    }).compileComponents();

    fixture = TestBed.createComponent(EmpresaFornecedor);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
