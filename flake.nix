{
  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs/nixpkgs-unstable";
    flake-utils.url = "github:numtide/flake-utils";
  };

  outputs =
    inputs@{
      self,
      ...
    }:
    inputs.flake-utils.lib.eachDefaultSystem (
      system:
      let
        pkgs = import inputs.nixpkgs {
          inherit system;
        };
        lib = pkgs.lib;
      in
      {
        devShell = pkgs.mkShell rec {
          buildInputs = with pkgs; [

            # build dependencies
            jdk21
            gradle

            # dev language server
            jdt-language-server

            # running
            libGL
            libpulseaudio
          ];

          LD_LIBRARY_PATH = "${lib.makeLibraryPath buildInputs}";
        };
      }
    );
}
