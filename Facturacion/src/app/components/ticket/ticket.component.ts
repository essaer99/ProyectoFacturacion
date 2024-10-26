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

  selectedFile: File | null = null; //selectedFile: Esta propiedad almacenará el archivo de imagen seleccionado. Inicialmente es null.
  isDragging = false;//Un booleano que indica si el usuario está arrastrando un archivo


//arrastra encima
  onDragOver(event: DragEvent) {
    event.preventDefault();//Previene el comportamiento predeterminado del navegador (como abrir el archivo arrastrado)
    this.isDragging = true;//Cambia el estado para reflejar que hay un archivo arrastrándose
  }


//arrasta y deja el archivo
  onDragLeave(event: DragEvent) {
    this.isDragging = false;//Cambia el estado isDragging a false cuando el archivo deja el área de arrastre.
  }



  onDrop(event: DragEvent) {
    event.preventDefault();//Previene el comportamiento predeterminado.
    this.isDragging = false;
    const files = event.dataTransfer?.files;//Obtiene los archivos arrastrados.
    if (files && files.length > 0) {
      this.validateAndSetFile(files[0]);//Si hay archivos, llama a validateAndSetFile con el primer archivo.
    }
  }
  
//Archivo seleccionado
  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;// Obtiene el elemento de entrada de archivo.
    if (input.files && input.files.length > 0) {
      this.validateAndSetFile(input.files[0]);//Si hay archivos seleccionados, llama a validateAndSetFile con el primer archivo.
    }
  }

//valida los archivos 
  validateAndSetFile(file: File) {
    const validTypes = ['image/jpeg', 'image/png', 'image/gif'];//validTypes: Un array que contiene los tipos MIME válidos para imágenes.
    if (validTypes.includes(file.type)) {
      this.selectedFile = file;//Si el tipo del archivo es válido, se asigna a selectedFile. Si no, muestra una alerta y resetea selectedFile a null.
    } else {
      alert('Por favor, selecciona un archivo de imagen válido (JPEG, PNG o GIF).');
      this.selectedFile = null;
    }
  }
//sube el archivo
  upload() {//Comprueba si hay un archivo seleccionado y, si es así, simula la subida (en este caso, solo muestra el nombre en la consola).
    if (this.selectedFile) {
      // Aquí puedes agregar la lógica para subir la imagen
      console.log('Subiendo archivo:', this.selectedFile.name);
      // Resetear el archivo seleccionado después de subir
      this.selectedFile = null;
    }
  }

}
