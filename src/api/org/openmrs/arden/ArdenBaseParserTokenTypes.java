/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
// $ANTLR 2.7.6 (2005-12-22): "ArdenRecognizer.g" -> "ArdenBaseParser.java"$

package org.openmrs.arden;

import java.io.*;
import antlr.CommonAST;
import antlr.collections.AST;
import antlr.*;
import org.openmrs.arden.MLMObject;
import org.openmrs.arden.MLMObjectElement;
import java.lang.Integer;

public interface ArdenBaseParserTokenTypes {
	int EOF = 1;
	int NULL_TREE_LOOKAHEAD = 3;
	int AND = 4;
	int WEIRD_IDENT = 5;
	int IS = 6;
	int ARE = 7;
	int WAS = 8;
	int WERE = 9;
	int COUNT = 10;
	int IN = 11;
	int LESS = 12;
	int GREATER = 13;
	int THE = 14;
	int THAN = 15;
	int FROM = 16;
	int BEFORE = 17;
	int AFTER = 18;
	int AGO = 19;
	int WRITE = 20;
	int AT = 21;
	int LET = 22;
	int NOW = 23;
	int BE = 24;
	int YEAR = 25;
	int YEARS = 26;
	int IF = 27;
	int IT = 28;
	int THEY = 29;
	int NOT = 30;
	int OR = 31;
	int THEN = 32;
	int READ = 33;
	int MINIMUM = 34;
	int MIN = 35;
	int MAXIMUM = 36;
	int MAX = 37;
	int LAST = 38;
	int FIRST = 39;
	int EARLIEST = 40;
	int LATEST = 41;
	int EVENT = 42;
	int WHERE = 43;
	int EXIST = 44;
	int EXISTS = 45;
	int PAST = 46;
	int DAYS = 47;
	int DAY = 48;
	int MONTH = 49;
	int MONTHS = 50;
	int WEEK = 51;
	int WEEKS = 52;
	int AVG = 53;
	int AVERAGE = 54;
	int SUM = 55;
	int MEDIAN = 56;
	int CONCLUDE = 57;
	int ELSE = 58;
	int ELSEIF = 59;
	int ENDIF = 60;
	int TRUE = 61;
	int FALSE = 62;
	int DATA = 63;
	int LOGIC = 64;
	int ACTION = 65;
	int MAINTENANCE = 66;
	int LIBRARY = 67;
	int FILENAME = 68;
	int MLMNAME = 69;
	int OF = 70;
	int TIME = 71;
	int WITHIN = 72;
	int CALL = 73;
	int WITH = 74;
	int TO = 75;
	int ANY = 76;
	int LITERAL_end = 77;
	int COLON = 78;
	int LITERAL_knowledge = 79;
	int LITERAL_title = 80;
	int ENDBLOCK = 81;
	int DOT = 82;
	int MINUS = 83;
	int UNDERSCORE = 84;
	int LITERAL_arden = 85;
	// "ASTM-E" = 86
	int INTLIT = 87;
	int LITERAL_version = 88;
	int DIGIT = 89;
	int LITERAL_institution = 90;
	int LITERAL_author = 91;
	int SEMI = 92;
	int LITERAL_specialist = 93;
	int LITERAL_date = 94;
	int LITERAL_validation = 95;
	int LITERAL_production = 96;
	int LITERAL_research = 97;
	int LITERAL_testing = 98;
	int LITERAL_expired = 99;
	int TIMES = 100;
	int ID = 101;
	int LPAREN = 102;
	int RPAREN = 103;
	// ":" = 104
	int LITERAL_T = 105;
	int LITERAL_t = 106;
	// "." = 107
	// "+" = 108
	// "-" = 109
	int LITERAL_Z = 110;
	int LITERAL_z = 111;
	int LITERAL_purpose = 112;
	int LITERAL_explanation = 113;
	int LITERAL_keywords = 114;
	int LITERAL_citations = 115;
	int LITERAL_SUPPORT = 116;
	int LITERAL_REFUTE = 117;
	int LITERAL_links = 118;
	int SINGLE_QUOTE = 119;
	int LITERAL_type = 120;
	// "data-driven" = 121
	int LITERAL_data_driven = 122;
	int COMMENT = 123;
	int ML_COMMENT = 124;
	int BECOMES = 125;
	int COMMA = 126;
	int LITERAL_EVENT = 127;
	int LITERAL_Event = 128;
	int ARDEN_CURLY_BRACKETS = 129;
	int LITERAL_PRESENT = 130;
	int LITERAL_NULL = 131;
	int LITERAL_BOOLEAN = 132;
	int LITERAL_NUMBER = 133;
	int LITERAL_DURATION = 134;
	int LITERAL_STRING = 135;
	int LITERAL_LIST = 136;
	int LITERAL_OBJECT = 137;
	int EQUAL = 138;
	int LITERAL_hour = 139;
	int LITERAL_hours = 140;
	int LITERAL_minute = 141;
	int LITERAL_minutes = 142;
	int LITERAL_second = 143;
	int LITERAL_seconds = 144;
	int LITERAL_OCCUR = 145;
	int LITERAL_Occur = 146;
	int LITERAL_occur = 147;
	int LITERAL_OCCURS = 148;
	int LITERAL_Occurs = 149;
	int LITERAL_occurs = 150;
	int LITERAL_OCCURRED = 151;
	int LITERAL_Occurred = 152;
	int LITERAL_priority = 153;
	int LITERAL_evoke = 154;
	int LITERAL_CALL = 155;
	int EQUALS = 156;
	int LITERAL_EQ = 157;
	int LT = 158;
	int LITERAL_LT = 159;
	int GT = 160;
	int LITERAL_GT = 161;
	int LTE = 162;
	int LITERAL_LE = 163;
	int GTE = 164;
	int LITERAL_GE = 165;
	int NE = 166;
	int LITERAL_NE = 167;
	int ACTION_OP = 168;
	int LITERAL_urgency = 169;
	int LITERAL_MERGE = 170;
	int LITERAL_SORT = 171;
	int LITERAL_DATA = 172;
	int LITERAL_SEQTO = 173;
	// "*" = 174
	// "/" = 175
	int STRING_LITERAL = 176;
	int TERM_LITERAL = 177;
	int WS = 178;
	int LBRACKET = 179;
	int RBRACKET = 180;
	int DOTDOT = 181;
	int NOT_EQUALS = 182;
	int PLUS = 183;
	int DIV = 184;
	int LCURLY = 185;
	int RCURLY = 186;
}
