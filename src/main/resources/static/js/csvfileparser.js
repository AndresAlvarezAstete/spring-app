const headerCargaSegmentos = ['rut', 'tipo_beneficiario']


const doParseCargaSegmentos = (csvData) => {
  return new Promise((resolve, reject) => {
    Papa.parse(csvData, {
      skipEmptyLines: 'greedy',
      delimiter: ';',
      worker: false,
      header: true,
      transformHeader: (header) => {
        return headerFormater(header);
      },
      step: (result, parser) => {
        console.log(result);
      },
      complete: (results) => {
        console.log(results.meta.fields);
        console.log(results);
        // error
        if (results.errors.length > 0) {
          reject(results.errors[0]);
        }

        let totalClientes = results.data.length;
        let csv = Papa.unparse(results);
        resolve({csv, totalClientes, results});
      },
      error: (err) => {
        reject(err);
      }
    });
  });
}
