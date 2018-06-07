[![GitHub license](https://img.shields.io/badge/license-GNU%20General%20Public%20License%20v3.0-blue.svg?style=flat)](http://www.gnu.org/licenses/)

# SDPROM - [Specification Driven Predictive Process Monitoring](https://doi.org/10.1007/978-3-319-91704-7_7) Tool

**Predictive business process monitoring** aims at forecasting the future information of a running business process based on the models extracted from business process execution logs (event logs). This tool, which is called **SDPROM**, is a prototype that implements the Specification-Driven Predictive Process Monitoring approach introduced in [[1](https://doi.org/10.1007/978-3-319-91704-7_7), [2](https://arxiv.org/abs/1804.00617)]. It is a [ProM](http://promtools.org) plug-in, and it enables us to
* specify the desired prediction tasks using the language that is introduced in [[1](https://doi.org/10.1007/978-3-319-91704-7_7), [2](https://arxiv.org/abs/1804.00617)], and
* create the corresponding prediction models based on the given specification.

Once the prediction models has been created, they can be used to provide predictive analysis service in business process monitoring. The [screencast](http://bit.ly/sdprom-screencast) of this tool, which explains the way how to use our tool ([ProM](http://promtools.org) plug-in), can be found in http://bit.ly/sdprom-screencast. The user manual  of our tools (including the instructions on how to reproduce our experiments) can be found in http://bit.ly/sdprom-manual. The binary distribution (.jar) of this tool can be found in http://bit.ly/sdprom-dist. More information about this work can be found in [[1](https://doi.org/10.1007/978-3-319-91704-7_7), [2](https://arxiv.org/abs/1804.00617)] and http://bit.ly/predictive-analysis. 

The following picture illustrates our approach:

![approach](/others/approach.jpg)

## License
This project is licensed under the GNU General Public License v3.0

```
Specification-Driven-Predictive-Process-Monitoring
Copyright (C) 2018 Ario Santoso (santoso.ario@gmail.com)

Licensed under GNU General Public License v3.0.
You may obtain a copy of the License at

      http://www.gnu.org/licenses/

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

```

## Compiling and Building the Project.

This project is a [Maven](http://maven.apache.org/) project. Thus, compiling and building the release binaries can be done using maven in the usual way. Currently we use Maven 3 and Java 8 to build the project.

## Contact
For more information, please contact the author: <br />
[Ario Santoso](http://bit.ly/ariosantoso) <br />
Email: santoso[dot]ario[at]gmail[dot]com <br />
Homepage: http://bit.ly/ariosantoso 

## Citing Our Work
You may use the following bibtex entries in order to cite this work:

```
@incollection{AS-BPMDS-18,
 author = “Ario Santoso”,
 title = "Specification-Driven Multi-Perspective Predictive Business Process Monitoring",
 booktitle = "Enterprise, Business-Process and Information Systems Modeling, BPMDS 2018, EMMSAD 2018",
 series = "Lecture Notes in Business Information Processing",
 volume="318",
 pages="97-113",
 publisher = "Springer",
 year = "2018”,
 doi="https://doi.org/10.1007/978-3-319-91704-7_7"
}
@TechReport{AS-CORR-18,
  author =       "Ario Santoso",
  title =        "Specification-Driven Multi-Perspective Predictive Business Process Monitoring (Extended Version)",
  institution =  "arXiv.org e-Print archive",
  year =         "2018",
  type =         "CoRR Technical Report",
  number =       "arXiv:1804.00617",
  note =         "Available at \protect\url{https://arxiv.org/abs/1804.00617}",
}
```

## References
[1] [Ario Santoso](http://bit.ly/ariosantoso): **[Specification-Driven Multi-Perspective Predictive Business Process Monitoring](https://doi.org/10.1007/978-3-319-91704-7_7)**. In Enterprise, Business-Process and Information Systems Modeling, BPMDS 2018, EMMSAD 2018. LNBIP. vol 318. pages 97-113. Springer.

[2] [Ario Santoso](http://bit.ly/ariosantoso): **[Specification-Driven Multi-Perspective Predictive Business Process Monitoring (Extended Version)](https://arxiv.org/abs/1804.00617)**. CoRR Technical Report arXiv:1804.00617, arXiv.org e-Print archive (2018), available at https://arxiv.org/abs/1804.00617.
