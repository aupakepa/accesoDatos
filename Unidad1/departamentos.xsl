<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:template match="/">
  <html>
<table border="1">
<xsl:apply-templates/>
</table>
    </html>
  </xsl:template>
  <xsl:template match="departamento/numero">
  <tr>
  <td>
  <xsl:value-of select="./text()"/>
  </td>
  <td>
  <xsl:value-of select="nombre"/>
  </td>
   <td>
  <xsl:value-of select="Localidad"/>
  </td>
  </tr>
  </xsl:template>
</xsl:stylesheet>