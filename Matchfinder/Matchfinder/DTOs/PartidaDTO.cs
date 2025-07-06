namespace Matchfinder.DTOs
{
    public class PartidaDTO
    {
        public string Id { get; set; }
        public string IdJogador1 { get; set; }
        public string IdJogador2 { get; set; }

        public PartidaDTO(String Id, String J1, String J2) 
        { 
            this.Id = Id;
            this.IdJogador1 = J1;
            this.IdJogador2 = J2;
        }
    }
}
