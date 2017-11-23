.. primitivefxmvc documentation master file, created by
   sphinx-quickstart on Thu Nov 23 13:17:10 2017.
   You can adapt this file completely to your liking, but it should at least
   contain the root `toctree` directive.

PrimitiveFXMVC
==========================================

PrimitiveFXMVC is a library which has a primitive MVC specifications and
a couple of units which provide a quick start to a JavaFX project.

Specification of Your Needs
------------------------

You should use this library when;

 * you want to make a quick start to you JavaFX application
 * you have a personal project (recommended)

However, we recommend you do not use this library in cases that:

 * you wish to utilize dependency management and inversion of control approaches in your project
 * your project depends vastly on POJOs (because this library heavily contains many units that is required to be implemented or extended)

Self-Criticism
--------------

This library might not hold the sense of classical MVC pattern due to the fact that it is aimed
to implement *the MVC pattern in sense of JavaFX*. To give an example, the term **controller**
is utilized for extending JavaFX controllers.

.. toctree::
   :maxdepth: 2
   :caption: Contents:

   index
   terms
   models
   views
   controllers



.. Indices and tables
   ==================

.. * :ref:`genindex`
   * :ref:`modindex`
   * :ref:`search`
