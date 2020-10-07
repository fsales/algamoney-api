import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-funcionario-form',
  templateUrl: './funcionario-form.component.html',
  styleUrls: ['./funcionario-form.component.css']
})
export class FuncionarioFormComponent implements OnInit {

  ultimoId = 0;
  nome = '';
  adicionado = false;

  @Output('criado') funcionarioAdicionado = new EventEmitter();


  ngOnInit(): void {
  }

  adicinar() {
    this.adicionado = true;
    const funcionario = {
      nome: this.nome,
      id: ++this.ultimoId
    };

    this.funcionarioAdicionado.emit(funcionario);
  }
}
