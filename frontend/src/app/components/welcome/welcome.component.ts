import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-welcome',
  standalone: true,
  imports: [RouterModule],  // ✅ Wichtig für routerLink
  template: `
    <div style="text-align: center; margin-top: 50px;">
      <h1>📚 Willkommen zu Thanh Bookstore!</h1>
      <p><a routerLink="/login">➡️ Zum Login</a></p>
    </div>
  `,
  styles: []
})
export class WelcomeComponent { }
