function displayPoP(src){

        titre="Agrandissement";
        w=open("",'image','toolbar=no,scrollbars=no,resizable=no');
        w.document.write("<HTML><HEAD><TITLE>"+titre+"</TITLE></HEAD>");
        w.document.write("<SCRIPT language=javascript>function checksize() { if (document.images[0].complete) { window.resizeTo(document.images[0].width+40,document.images[0].height+70); window.focus();} else { setTimeout('checksize()',250) } }</"+"SCRIPT>");
        w.document.write("<BODY onload='checksize()' onblur='window.close()' onclick='window.close()' leftMargin=0 topMargin=0 marginwidth=0 marginheight=0>");
        w.document.write("<TABLE width='100%' border='0' cellspacing='0' cellpadding='0' height='100%'><TR>");
        w.document.write("<TD valign='middle' align='center'><IMG src='"+src+"' border=0 alt='Mon image'>");
        w.document.write("</TD></TR></TABLE>");
        w.document.write("</BODY></HTML>");
        w.document.close();
}