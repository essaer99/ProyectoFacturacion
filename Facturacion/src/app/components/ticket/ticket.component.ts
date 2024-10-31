import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';


@Component({
  selector: 'app-ticket',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './ticket.component.html',
  styleUrl: './ticket.component.css'
})
export class TicketComponent {

  selectedFile: File | null = null; // Almacena el archivo de imagen seleccionado. Inicialmente es null.
  isDragging = false; // Indica si el usuario está arrastrando un archivo.
  previewUrl: string | ArrayBuffer | null = null; // Almacena la URL de la vista previa de la imagen.

  // Arrastra encima
  onDragOver(event: DragEvent) {
    event.preventDefault(); // Previene el comportamiento predeterminado del navegador (como abrir el archivo arrastrado).
    this.isDragging = true; // Cambia el estado para reflejar que hay un archivo arrastrándose.
  }

  // Arrastra y deja el archivo
  onDragLeave(event: DragEvent) {
    this.isDragging = false; // Cambia el estado isDragging a false cuando el archivo deja el área de arrastre.
  }

  onDrop(event: DragEvent) {
    event.preventDefault(); // Previene el comportamiento predeterminado.
    this.isDragging = false;
    const files = event.dataTransfer?.files; // Obtiene los archivos arrastrados.
    if (files && files.length > 0) {
      this.validateAndSetFile(files[0]); // Llama a validateAndSetFile con el primer archivo.
    }
  }

  // Archivo seleccionado
  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement; // Obtiene el elemento de entrada de archivo.
    if (input.files && input.files.length > 0) {
      this.validateAndSetFile(input.files[0]); // Llama a validateAndSetFile con el primer archivo.
    }
  }

  // Valida los archivos
  validateAndSetFile(file: File) {
    const validTypes = ['image/jpeg', 'image/png', 'image/gif']; // Tipos MIME válidos para imágenes.
    if (validTypes.includes(file.type)) {
      this.selectedFile = file; // Asigna a selectedFile.
      this.createImagePreview(file); // Crea la vista previa de la imagen.
    } else {
      alert('Por favor, selecciona un archivo de imagen válido (JPEG, PNG o GIF).');
      this.selectedFile = null; // Resetea selectedFile a null.
      this.previewUrl = null; // Resetea la vista previa.
    }
  }

  // Crea la vista previa de la imagen
  createImagePreview(file: File) {
    const reader = new FileReader();
    reader.onload = (e: ProgressEvent<FileReader>) => {
      this.previewUrl = e.target?.result ?? null; // Asigna null si result es undefined
    };
    reader.readAsDataURL(file);
  }

  // Sube el archivo
  upload() {
    if (this.selectedFile) {
      // Aquí puedes agregar la lógica para subir la imagen.
      console.log('Subiendo archivo:', this.selectedFile.name);
      // Resetear el archivo seleccionado después de subir.
      this.selectedFile = null;
      this.previewUrl = null; // Resetea la vista previa.
    }
  }

}
