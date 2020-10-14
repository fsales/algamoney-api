## Angular e Spring - curso Algaworks


#  Angular CLI
* Instalação 
  [Angular CLI](https://cli.angular.io/)
  [Angular Material](https://material.angular.io/)
  [PrimeNG](https://www.primefaces.org/primeng/)

* Verificar versão
  
   ```sh
      $ ng --version
   ```
* Criar projeto

  ```sh
    $ ng new {nome_projeto}
  ```

* Inciar projeto

  ```sh
    $ ng serve --port 4200
  ```
* Acessar projeto

  ```sh
    $ http://localhost:4200/
  ```

* Criando componente
    1) 
    ```sh
      $ ng generate component bem-vindo
    ```
    1) 
    ```sh
      $ ng g c bem-vindo --spec=false
    ```

* Instalar bootstrap

    ```sh
      $ npm install bootstrap --save
    ```


* Data binding
  
  Interpolação
  
    ```sh
      $ {{nome}}
    ```
  Property binding
  
    ```sh
      $ [prop]="nome"
    ```
  Event binding
    ```sh
      $ (click)="salvar()"
    ```
  Two-way binding
    ```sh
      $ [(ngModel)]="nome"
    ```
* Diretivas
    Componentes
    ```sh
      $ <lista-pessoas><lista-pessoas/>
    ```

    Estrutural
    ```sh
      $ <h2 *ngIf="logado">Olá {{nomeUsuario}}</h2>
    ```

    Diretivas de atributos
    ```sh
      $ <h2 [style.color]="red">Olá {{nomeUsuario}}</h2>

      $ <h2 atencao>Olá {{nomeUsuario}}</h2>
    ```


### Frontend
- [x] Instalando e criando projeto
- [X] Data binding
- [x] Introdução às diretivas


  


