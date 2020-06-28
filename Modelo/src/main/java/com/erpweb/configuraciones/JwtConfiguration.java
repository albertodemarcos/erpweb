/*package com.erpweb.configuraciones;

public class JwtConfiguration {

	public static final String LLAVE_SECRETA = "clave.secreta.tfg";

	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEogIBAAKCAQEAr8/8ZO979wapi04tyleMlie4Tr0HTQhsVU6jcpdCfYTXl7/R\r\n"
			+ "Q+mUuxnMZHMqVHF1rsLjAdrVRCpThKS4iX5kCUPMcg1/rrcUqmbN/Cdt8Cie0bnq\r\n"
			+ "mOVjvjuoPuI2pV0otTDqD6eSfZszfvyqBkgev17JrTlo8FnN29WkLPnKJKhEziIg\r\n"
			+ "E+2ajrTJapiQsMNR0ASYl5poSBtyBctcReNIxqLt/IbVlHIOjS0aExYwRRxsFkHw\r\n"
			+ "n62pKYENHwWsJVgQ5ufLk/g4gk1MFZycdM8vVIhnw98RaiLP4OJhLaynGyPdJqpg\r\n"
			+ "141BHbCkxvIdH1vdTAIzlF4TbsLvJmPh64chMwIDAQABAoIBAEoZmUFZhqAZGMB0\r\n"
			+ "FOyhdcmbuBzHoxoeoLpbyJv1kLqikNOOrz62Zk9tki4MNB0fLQCZd2BCwXxXcpgH\r\n"
			+ "bRfWQ4d8IeE4O0aCN52ScuMCI14DYNz4RR5oIF//eOv4IBwWZZ/om945PI7gKRYu\r\n"
			+ "m+LF5t+1rb7q4YXb893h765dIYvNBSqAo4Rmued/t0rQUoSpnVglnEiQljesLCT/\r\n"
			+ "c7J0e1C3+nf9VjDPp0T97lLxsrRAytXSPvfIzfOZjDIfUnO9NzDZ9+VlfWuV2Y03\r\n"
			+ "+4IxeCjlk1kBabQ0OJVyqNpd8TMOkg9yEkdUc1ITk+mT2YrAABeDvY9nW2dkq3xV\r\n"
			+ "WtppRbECgYEA3Jr1tKvGj3kFIPnURhB12BcCapG5ELH3StSCSFsowYd79DSO5pXE\r\n"
			+ "Bkn3MF7oTax80vFAncSgLY81bsEym6CknBW2zfpPHGb004mPJwog0hqhKe5e+2dT\r\n"
			+ "BJwCCDgMP4dHo8HuBUJ0/MV1TNf5QIND/w2QaZkvRoPgTwia4Hl0iK8CgYEAzAU5\r\n"
			+ "hjf+bZ6m3VZRzRdzU2k9TjMjEDh+dkGLlA6J1iSiRA4SwOBDhiJakJfpZefCEKPf\r\n"
			+ "rHCMFfdjN3668aVrhzkE3j5Itxzti4Sa/g/myMlyuOhFLBKDKLr51OZdNz6+zBr6\r\n"
			+ "4c6H6+eeGmbm49oLbDcbFh3DLFon55mnNCADSL0CgYEAi8gMZBxrxkEShOCIJpUL\r\n"
			+ "do6t00TelLuPS/T3UE2it23XltcurOC8wpdfb2DTpVPkC+0d2lnafMO/QZxdKstR\r\n"
			+ "Qi1oFX/DXOXHv7nr4PrD7R0+fUkzGe1QZx1LFFYfPKnFNPgOj3qakgez2asHnl8O\r\n"
			+ "WLAJjz+m+vFY0ftL12xd8LUCf3q6qeATzvoidj6vroWxla6XoElqea1ITsrUoyJt\r\n"
			+ "nH01jGdkD+P3BKesAs74k265/AQGkIcrLRoJt6WqlsrGt+igZE1NKnD89ej33ET5\r\n"
			+ "S9t9U4XoM7RPbpcR4zgvW+Pf2ubtU/6CP0GqE3HOdRacFEME40ZqoP4NPfQUeoDg\r\n"
			+ "wRkCgYEAtLru2yZ9Mx9sh07IM/ym4wS2GanJWplYnCNbGzvtwDbJvP5JvHTJlVA2\r\n"
			+ "gGk9qPCJ1zIfVVNw3XjhCtAVkz/4g9fE6kWWUMfTqr051TOF6y18X+IqGRl9RON6\r\n"
			+ "w8XQhH3pSp2VaBSNj2RrEIaAZHbMO3B9VhRJrOdnRpusswUgFKc=\r\n" 
			+ "-----END RSA PRIVATE KEY-----";

	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAr8/8ZO979wapi04tyleM\r\n"
			+ "lie4Tr0HTQhsVU6jcpdCfYTXl7/RQ+mUuxnMZHMqVHF1rsLjAdrVRCpThKS4iX5k\r\n"
			+ "CUPMcg1/rrcUqmbN/Cdt8Cie0bnqmOVjvjuoPuI2pV0otTDqD6eSfZszfvyqBkge\r\n"
			+ "v17JrTlo8FnN29WkLPnKJKhEziIgE+2ajrTJapiQsMNR0ASYl5poSBtyBctcReNI\r\n"
			+ "xqLt/IbVlHIOjS0aExYwRRxsFkHwn62pKYENHwWsJVgQ5ufLk/g4gk1MFZycdM8v\r\n"
			+ "VIhnw98RaiLP4OJhLaynGyPdJqpg141BHbCkxvIdH1vdTAIzlF4TbsLvJmPh64ch\r\n" + "MwIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";
}
*/