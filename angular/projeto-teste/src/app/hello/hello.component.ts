import { Component } from '@angular/core';

@Component({
  selector: 'app-hello',
  template: `
        <h2>
            Hello {{nome}}
        </h2>
    `
})
export class HelloComponent {
  nome = 'Fábio Sales';
}
