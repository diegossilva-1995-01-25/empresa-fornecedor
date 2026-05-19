CREATE DATABASE "empresa-fornecedor"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;
    
CREATE TABLE public.usuario
(
    cpf character varying NOT NULL,
    senha character varying NOT NULL,
    nome character varying NOT NULL,
    PRIMARY KEY (cpf)
);

ALTER TABLE public.usuario
    OWNER to postgres;
    
CREATE TABLE public.empresa
(
    cnpj character varying NOT NULL,
    nome_fantasia character varying NOT NULL,
    cep bigint NOT NULL,
    cpf_usuario character varying NOT NULL,
    PRIMARY KEY (cnpj),
    CONSTRAINT cpf_usuario FOREIGN KEY (cpf_usuario)
        REFERENCES public.usuario (cpf) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE public.empresa
    OWNER to postgres;
    
CREATE TABLE public.fornecedor
(
    cpf_cnpj character varying NOT NULL,
    nome character varying NOT NULL,
    email character varying NOT NULL,
    cep bigint NOT NULL,
    rg character varying,
    nasc integer,
    cpf_usuario character varying NOT NULL,
    PRIMARY KEY (cpf_cnpj),
    CONSTRAINT cpf_usuario FOREIGN KEY (cpf_usuario)
        REFERENCES public.usuario (cpf) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE public.fornecedor
    OWNER to postgres;
    
CREATE TABLE public.empresa_fornecedor
(
    empresa_cnpj character varying NOT NULL,
    fornecedor_cpf_cnpj character varying NOT NULL,
    PRIMARY KEY (empresa_cnpj, fornecedor_cpf_cnpj),
    CONSTRAINT empresa_cnpj FOREIGN KEY (empresa_cnpj)
        REFERENCES public.empresa (cnpj) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fornecedor_cpf_cnpj FOREIGN KEY (fornecedor_cpf_cnpj)
        REFERENCES public.fornecedor (cpf_cnpj) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE public.empresa_fornecedor
    OWNER to postgres;