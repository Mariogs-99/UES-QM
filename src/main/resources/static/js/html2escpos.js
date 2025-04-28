/* 
 * Alcides Nolasco
 * Funcion js para convertir html a formato esc/pos raw data
 * 06/06/2024
 * 
 */

function html2escpos(htmlString) {
    const ESC = '\x1B'; // ESC byte in ASCII
    const NEW_LINE = '\x0A'; // New line byte in ASCII map-> <div>
    const CENTER = ESC + 'a' + '\x01'; // Centering
    const LEFT = ESC + 'a' + '\x00'; // Left alignment
    const BOLD_ON = ESC + 'E' + '\x01'; // Bold on
    const BOLD_OFF = ESC + 'E' + '\x00'; // Bold off
    const H1_SIZE = ESC + '!' + '\x38'; // Double height and width
    const H2_SIZE = ESC + '!' + '\x30'; // Double height
    const H3_SIZE = ESC + '!' + '\x20'; // Emphasized
    const H4_SIZE = ESC + '!' + '\x10'; // Normal text
    const RESET_SIZE = ESC + '!' + '\x00'; // Reset text size

    let escPosString = ESC + "@"; // Initialize printer
    // Parse the HTML string
    const parser = new DOMParser();
    const doc = parser.parseFromString(htmlString, 'text/html');

    function processNode(node) {
        if (node.nodeType === Node.TEXT_NODE) {
            escPosString += node.textContent;
        } else if (node.nodeType === Node.ELEMENT_NODE) {
            switch (node.tagName.toLowerCase()) {
                case 'center':
                    escPosString += CENTER;
                    node.childNodes.forEach(processNode);
                    escPosString += LEFT;
                    break;
                case 'b':
                    escPosString += BOLD_ON;
                    node.childNodes.forEach(processNode);
                    escPosString += BOLD_OFF;
                    break;
                case 'h1':
                    escPosString += H1_SIZE;
                    node.childNodes.forEach(processNode);
                    escPosString += RESET_SIZE;
                    break;
                case 'h2':
                    escPosString += H2_SIZE;
                    node.childNodes.forEach(processNode);
                    escPosString += RESET_SIZE;
                    break;
                case 'h3':
                    escPosString += H3_SIZE;
                    node.childNodes.forEach(processNode);
                    escPosString += RESET_SIZE;
                    break;
                case 'h4':
                    escPosString += H4_SIZE;
                    node.childNodes.forEach(processNode);
                    escPosString += RESET_SIZE;
                    break;
                case 'div':
                    node.childNodes.forEach(processNode);
                    escPosString += NEW_LINE;
                    break;
                default:
                    node.childNodes.forEach(processNode);
            }
        }
    }

    // Process the document body
    doc.body.childNodes.forEach(processNode);
    escPosString += ESC + "d"+"\x08"; // Feed 4 lines 
    escPosString += ESC+ "\x6D"; // and cut paper
    return escPosString;
}

const htmlToRtfMap = {
  'center': '\\qc ',
  '/center': '\\ql ',
  'b': '\\b ',
  '/b': '\\b0 ',
  'h1': '\\fs48 ',
  '/h1': '\\fs24 ',
  'h2': '\\fs36 ',
  '/h2': '\\fs24 ',
  'h3': '\\fs32 ',
  '/h3': '\\fs24 ',
  'h4': '\\fs28 ',
  '/h4': '\\fs24 ',
  'br': '\\line ',
  'p': '\\par ',
  '/p': '\\par '
};

function html2rtf(html) {
  let rtf = '{\\rtf1\\ansi\\deff0';
  const parser = new DOMParser();
  const doc = parser.parseFromString(html, 'text/html');

  function processNode(node) {
    if (node.nodeType === Node.TEXT_NODE) {
      rtf += node.textContent.replace(/\n/g, '\\line ');
    } else if (node.nodeType === Node.ELEMENT_NODE) {
      const tag = node.tagName.toLowerCase();
      if (htmlToRtfMap[tag]) {
        rtf += htmlToRtfMap[tag];
      }
      node.childNodes.forEach(processNode);
      if (htmlToRtfMap[`/${tag}`]) {
        rtf += htmlToRtfMap[`/${tag}`];
      }
    }
  }

  doc.body.childNodes.forEach(processNode);
  rtf += '}';

  return rtf;
}




